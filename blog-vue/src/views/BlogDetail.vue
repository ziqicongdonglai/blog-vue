<template>
  <div>
    <Header></Header>
    <div class="m-blog">
      <h2>{{ blog.title }}</h2>
      <el-link icon="el-icon-edit" v-if="ownBlog">
        <router-link :to="{ name: 'BlogEdit', params: { blogId: blog.id } }">
          编辑
        </router-link>
      </el-link>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>
  </div>
</template>
<script>
import "github-markdown-css";
import Header from "../components/Header";
export default {
  name: "BlogDetail",
  components: { Header },
  data() {
    return {
      blog: {
        id: "",
        title: "",
        content: "",
      },
      ownBlog: false,
    };
  },
  created() {
    const blogId = this.$route.params.blogId;
    console.log(blogId);
    const _this = this;
    this.$axios.get("/blog/" + blogId).then((res) => {
      const blog = res.data.data;
      _this.blog.id = blog.id;
      _this.blog.title = blog.title;
      const MardownIt = require("markdown-it");
      const md = new MardownIt();
      const result = md.render(blog.content);
      _this.blog.content = result;
      _this.ownBlog = blog.userId === _this.$store.getters.getUser.id;
    });
  },
};
</script>
<style lang=""></style>
