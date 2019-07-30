<template>
  <div :style="note" style="height:100vh;width:100%;position:fixed">
    <div id="login">
      <h1 style="color:white">家庭理财系统</h1>
      <form id="from" :model="formmsg">
        <div class="form-group">
          <label for="account" style="color: white">Account</label>
          <input
            type="text"
            v-model="formmsg.username"
            class="form-control"
            id="account"
            placeholder="Account"
          />
        </div>
        <div class="form-group">
          <label for="password" style="color: white">Password</label>
          <input
            type="password"
            class="form-control"
            id="password"
            placeholder="Password"
            v-model="formmsg.password"
          />
        </div>
        <span id="tip" style="color:red;">{{motton}}</span>
      </form>
      <button @click="goLogin" id="lgb" type="button" class="btn btn-info">登录</button>
      <!-- Form -->
      <button id="rgb" class="btn btn-info" type="text" @click="dialogFormVisible = true">注册</button>

      <el-dialog title="填写注册信息" :visible.sync="dialogFormVisible">
        <el-form :model="form">
          <el-form-item class="iptl" label="账号" :label-width="formLabelWidth">
            <el-input v-model="form.account" auto-complete="on"></el-input>
          </el-form-item>

          <el-form-item class="iptl" label="密码" :label-width="formLabelWidth">
            <el-input v-model="form.password" auto-complete="off" type="password"></el-input>
          </el-form-item>

          <el-form-item class="iptl" label="再次确认密码" :label-width="formLabelWidth">
            <el-input v-model="form.againpassword" auto-complete="off" type="password"></el-input>
          </el-form-item>

          <el-form-item class="iptl" label="姓名" :label-width="formLabelWidth">
            <el-input v-model="form.name" auto-complete="on"></el-input>
          </el-form-item>

          <el-form-item class="iptl" label="年龄" :label-width="formLabelWidth">
            <el-input v-model="form.age" auto-complete="on"></el-input>
          </el-form-item>

          <el-form-item class="iptl" label="家庭称呼" :label-width="formLabelWidth">
            <el-input v-model="form.familyCall" auto-complete="on"></el-input>
          </el-form-item>

          <el-form-item class="iptl" label="工资" :label-width="formLabelWidth">
            <el-input v-model="form.wages" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="goRegister">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>

export default {
  data() {
    return {
      motton:"",
      note: {
        backgroundImage: "url(" + require("./img/bg.jpg") + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: "100% 100%"
        //  marginTop: "50px",
      },
      dialogTableVisible: false,
      dialogFormVisible: false,
      formmsg: {
        username: "",
        password: ""
      },
      form: {
        account: "",
        password: "",
        name: "",
        age: "",
        familyCall: "",
        wages: ""
      },
      formLabelWidth: "120px"
    };
  },
  methods: {
    //点击登录按钮
    subimt: function() {},
    goLogin: function() {
      var that = this;
      if (this.formmsg.account == "") {
        this.motton = "用户名不能为空！";
      } else if (this.formmsg.password == "") {
        this.motton = "密码不能为空！";
      } else {
        this.$axios
          .get("/login", {
            params: {
              account: this.formmsg.username,
              password: this.formmsg.password
            }

          })
          .then(res => {
            if (res.data.code == "200") {
              //this.$cookies.set("token", res.data.data, 3);
              window.localStorage.setItem("username", this.formmsg.username);
              window.localStorage.setItem("token", res.data.data);
              console.log(res);
              this.$router.push({ path: "/main" });
            } else {
              console.log(res);
              this.motton = res.data.data;
              this.formmsg.password = "";
            }
          });
      }
    },

    goRegister: function() {
      var that = this;
      console.log(this.form);

      this.$axios
        .get("/register", {
          params: {
            account: this.form.account,
            password: this.form.password,
            name: this.form.name,
            age: this.form.age,
            familyCall: this.form.familyCall,
            wages: this.form.wages
          }

        })
        .then(res => {
          if (res.data.code == "200") {
            that.dialogFormVisible = false;
            alert("注册成功");
            console.log(this.dialogFormVisible);
          } else {
            alert("注册失败");

            that.dialogFormVisible = false;
            console.log(this.dialogFormVisible);
          }
        })
        .catch(res => {
          that.dialogFormVisible = false;
          console.log(this.dialogFormVisible);
        });
    }
  }
};
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
}
#login {
  width: 400px;
  height: 500px;
  margin: 100px auto;
}
h1 {
  text-align: center;
}
#lgb {
  width: 200px;
  margin-top: 20px;
}
#rgb {
  width: 144px;
  margin-top: 20px;
  margin-left: 50px;
}
.iptl {
  width: 400px;
}
</style>
