<template>
  <div></div>
</template>

<script>
export default {
  methods: {
    AuthConf: function() {
      var that = this;
      that.$axios.interceptors.request.use(
        config => {
          config.headers["Content-Type"] = "application/json";
          let token = localStorage.getItem("token");
          if (token) {
            config.headers.common["Authorization"] = token;
          }
          return config;
        },
        err => {
          return Promise.reject(err);
        }
      );
    }
  },
  mounted() {
    this.AuthConf();
    this.$confirm("是否格式化所有成员的收支信息？", "提示", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning"
    })
      .then(() => {
        this.$axios({
          methods: "get",
          url: "/database/format"
        })
          .then(res => {
            if (res.data.code == "200") {
              this.$message({
                type: "success",
                message: res.data.data
              });
              console.log(res.data.data);
            } else {
              this.$message({
                type: "info",
                message: "您无权限进行此操作!"
              });
            }
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "您无权限进行此操作!"
            });
          });
      })
      .catch(() => {
        this.$message({
          type: "info",
          message: "格式化已取消！"
        });
      });
  }
};
</script>

<style scoped>
</style>
