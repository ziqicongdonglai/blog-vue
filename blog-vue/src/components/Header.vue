<template>
  <div class="m-content">
    <h3>欢迎来到我的博客</h3>
    <div class="block">
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
      <div>{{ user.username }}</div>
    </div>

    <div class="m-action">
      <span><el-link href="/blogs">主页</el-link></span>
      <el-divider direction="vertical"></el-divider>
      <span>
        <el-link type="success"
          ><router-link :to="{ name: 'BlogAdd' }"
            >发表博客</router-link
          ></el-link
        >
      </span>

      <el-divider direction="vertical"></el-divider>
      <span v-show="!hasLogin"
        ><el-link type="primary" href="/login">登录</el-link></span
      >

      <span v-show="hasLogin"
        ><el-link type="danger" @click="logout">退出</el-link></span
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      user: {
        username: "请先登录",
        avatar: "https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/ssy.jpg",
      },
      hasLogin: false,
    };
  },
  methods: {
    logout() {
      const _this = this;
      _this.$axios
        .get("/logout", {
          headers: {
            Authorization: localStorage.getItem("token"),
          },
        })
        .then(() => {
          _this.$store.commit("REMOVE_INFO");
          _this.$router.push("/login");
        });
    },
  },
  created() {
    if (this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username;
      this.user.avatar = this.$store.getters.getUser.avatar;
      this.hasLogin = true;
    }
  },
};
</script>

<style scoped>
.m-content {
  max-width: 960px;
  margin: 0 auto;
  text-align: center;
}

.m-action {
  margin: 10px 0;
}
</style>
