<template>
  <div>
    <el-container>
      <el-header class="header">
        <img
          class="m-logo"
          src="https://niit-soft.oss-cn-hangzhou.aliyuncs.com/img/cover/2.jpg"
          alt=""
        />
      </el-header>
      <el-main>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="⽤户名" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')"
              >登录</el-button
            >
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        username: "zhangke",
        password: "123456",
      },
      rules: {
        username: [
          { required: true, message: "请输⼊⽤户名", trigger: "blur" },
          {
            min: 3,
            max: 15,
            message: "⻓度在 3 到 15 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输⼊密码", trigger: "change" },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;
          this.$axios.post("/login", this.ruleForm).then((res) => {
            console.log("请求数据：" + res.data);
            const jwt = res.headers["authorization"];
            const userInfo = res.data.data;
            // 把数据共享到store
            _this.$store.commit("SET_TOKEN", jwt);
            _this.$store.commit("SET_USER_INFO", userInfo);
            // 获取user
            console.log(_this.$store.getters.getUser);
            // 跳转到 blogs
            _this.$router.push("/blogs");
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
<style scoped>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  height: 150px;
}
.m-logo {
  width: 100%;
  height: 100%;
}
.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}
.el-main {
  background-color: #e9eef3;
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
.demo-ruleForm {
  max-width: 500px;
  margin: 0 auto;
}
</style>
