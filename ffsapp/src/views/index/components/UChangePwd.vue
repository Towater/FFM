<template>
  <div id="box1">
    <p class="text-muted">为保障您的信息安全，修改密码前请填写原密码</p>
    <p>当前用户:{{mingzi}}</p>
    <el-form
      :model="ruleForm2"
      status-icon
      :rules="rules2"
      ref="ruleForm2"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="原密码" prop="oldPassword">
        <el-input v-model="ruleForm2.oldPassword" class="ipt"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="newPassword">
        <el-input type="password" v-model="ruleForm2.newPassword" class="ipt"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirm">
        <el-input type="password" v-model="ruleForm2.confirm" class="ipt"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="password" @click="submitForm('ruleForm2')" class="btn1">提交</el-button>
        <el-button @click="resetForm('ruleForm2')" class="btn2">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    var checkOldPassword = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("密码不能为空"));
      }
      setTimeout(() => {
        if (value === "") {
          callback(new Error("请输入密码"));
        } else {
          if (value < 18) {
            callback(new Error("密码不正确"));
          } else {
            callback();
          }
        }
      }, 1000);
    };
    var checkNewPassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm2.confirm !== "") {
          this.$refs.ruleForm2.validateField("confirm");
        }
        callback();
      }
    };
    var checkConfirm = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm2.newPassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      mingzi: "",
      ruleForm2: {
        newPassword: "",
        confirm: "",
        oldPassword: ""
      },
      rules2: {
        newPassword: [{ validator: checkNewPassword, trigger: "blur" }],
        confirm: [{ validator: checkConfirm, trigger: "blur" }],
        oldPassword: [{ validator: checkOldPassword, trigger: "blur" }]
      }
    };
  },
  methods: {
    // 		this.$axios
    //   .get(" http://localhost:8080/ffs/income-info/byid/9", {
    // 	  				oldPassword: this.ruleForm2.oldPassword,
    // 						newPassword: this.ruleForm2.newPassword,
    // 						confirm: this.ruleForm2.confirm,

    //   })
    //   .then(res => {
    //     if (res.data.code == "200") {
    //       // console.log(res.data);
    //     } else {
    //       console.log(res.data.data);
    //     }
    //   });
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
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.AuthConf();
          this.$axios({
            methods:"get",
            url:"/updatePassword",
            params:{
              oldPassword: this.ruleForm2.oldPassword,
					    newPassword: this.ruleForm2.newPassword,
					    confirm: this.ruleForm2.confirm
            }
          }).then(res => {
              if (res.data.code == "200") {
                alert(res.data.data);
              } else {
                alert(res.data.data);
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  mounted() {
    this.mingzi = window.localStorage.getItem("username");
  }
};
</script>
<style scoped>
.text-muted {
  margin-left: 80px;
  margin-top: 20px;
}
#box1 {
  margin-left: 150px;
  margin-top: 50px;
}
.ipt {
  width: 360px;
}
.btn1 {
  margin-left: 170px;
}
.btn2 {
  margin-left: 10px;
}
p {
  margin-left: 200px;
}
</style>