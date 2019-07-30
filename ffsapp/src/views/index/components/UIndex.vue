<template>
  <div>
    <el-row class="lab">
      <label>账号</label>
    </el-row>
    <el-row>
      <el-input style="width:80%" v-model="UserInfo.account" value="UserInfo.account"></el-input>
    </el-row>
    <el-row class="lab">
      <label>姓名</label>
    </el-row>
    <el-row>
      <el-input style="width:80%" v-model="UserInfo.name" value="UserInfo.name"></el-input>
    </el-row>
    <el-row class="lab">
      <label>年龄</label>
    </el-row>
    <el-row>
      <el-input style="width:80%" v-model="UserInfo.age" value="UserInfo.age"></el-input>
    </el-row>
    <el-row class="lab">
      <label>家庭称呼</label>
    </el-row>
    <el-row>
      <el-input style="width:80%" v-model="UserInfo.familyCall" value="UserInfo.familyCall"></el-input>
    </el-row>
    <el-row class="lab">
      <label>工资</label>
    </el-row>
    <el-row>
      <el-input style="width:80%" v-model="UserInfo.wages" value="UserInfo.wages"></el-input>
    </el-row>
    <el-row class="lab">
      <el-col offset="18">
        <el-button type="primary" round @click="Update">保存</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      UserInfo: {
          id: "",
          account: "",
          password: "",
          name: "",
          age: "",
          familyCall: "",
          wages: ""
      }
    };
  },
  methods: {
    GetUserInfo() {
      var that = this;
      this.AuthConf();
      this.$axios({
        method: "get",
        url: "/selectUserByid"
      }).then(res => {
        if (res.data.code == "200") {
          that.UserInfo = res.data.data;
          console.log(this.UserInfo);
        }
      });
    },
    Update() {
      var that = this;
      console.log(that.UserInfo.id);
      this.AuthConf();
      this.$axios({
        params: {
          id: that.UserInfo.id,
          account: that.UserInfo.account,
          name: that.UserInfo.name,
          age: that.UserInfo.age,
          familyCall: that.UserInfo.familyCall,
          wages: that.UserInfo.wages
        },
        menthod: "get",
        url: "/updateUser"
      }).then(res => {
        if (res.data.code == "200") {
          alert("修改成功");
        }
      });
    },
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
    this.GetUserInfo();
  }
};
</script>
<style scoped>
.lab {
  margin-top: 30px;
}
</style>