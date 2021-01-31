<template>
  <div>
    <Herder></Herder>
    <div class="mblog">
      <div class="block">
        <el-timeline>
          <el-timeline-item :timestamp="blog.created" placement="top" v-for="blog in blogs">
            <el-card>
              <h4>
                <router-link :to="{name: 'BlogDetail',params: {blogId: blog.id}}">
                {{ blog.title }}
                </router-link>
              </h4>
              <p>{{ blog.description }}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>
      <el-pagination class="mpage"
        background
        layout="prev, pager, next"
        :total="total"
        :current-page="currentPage"
        :page-size="pageSize"
        @current-change="page">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import Herder from '../components/Header.vue'
export default {
  name: 'Blogs',
  components: {Herder},
  data () {
    return {
      blogs: {},
      currentPage: 1,
      total: 0,
      pageSize: 5
    }
  },
  methods: {
    page (currentPage) {
      const _this = this
      _this.$axios.get('/blog/blogs?currentPage=' + currentPage).then(res => {
        console.log(res)
        _this.blogs = res.data.data.records
        _this.currentPage = res.data.data.current
        _this.total = res.data.data.pages
        _this.pageSize = res.data.data.size
      })
    }
  },
  created () {
    this.page(1)
  }
}
</script>

<style scoped>
    .mblog {
      max-width: 1000px;
      margin: 0 auto;
    }
    .mpage {
      text-align: center;
    }
</style>
