<template>
  <el-container>
    <el-header>mituh   blog</el-header>
    <el-main>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit('form')">登录</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'Login',
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    return {
      form: {
        username: 'mituh',
        password: '1234567890'
      },
      rules: {
        password: [
          {validator: validatePass, trigger: 'blur'}
        ],
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    onSubmit (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 提交逻辑
          this.$axios.post('/login', this.form).then((res) => {
            const token = res.headers['authorization']
            const userInfo = res.data.data

            // 把数据共享出去
            _this.$store.commit('SET_TOKEN', token)
            _this.$store.commit('SET_USERINFO', userInfo)

            // 获取
            console.log(_this.$store.getters.getUser)
            // 跳转页面
            _this.$router.push('blog/blogs')
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    /*background-color: #E9EEF3;*/
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  .ruleForm{
    max-width: 500px;
    margin: 0 auto;
  }
</style>
