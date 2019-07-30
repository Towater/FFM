<template>
  <div id="login">
    <h1>家庭理财系统</h1>
    <form id="from"  :model="formmsg">
      <div class="form-group">
        <label for="username">User</label>
        <input
          type="text"
          @change="getUsername"
          v-model="formmsg.username"
          class="form-control"
          id="username"
          placeholder="User"
        >
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input
          type="password"
          @change="getPassword"
          v-model="formmsg.password"
          class="form-control"
          id="password"
          placeholder="Password"
        >
      </div>
    </form>
    <button  id="lgb" type="button" class="btn btn-info" @click="login">登录</button>
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

        <el-form-item class="iptl" label="家庭电话" :label-width="formLabelWidth">
          <el-input v-model="form.familyCall" auto-complete="on"></el-input>
        </el-form-item>

        <el-form-item class="iptl" label="工资" :label-width="formLabelWidth">
          <el-input v-model="form.wages" auto-complete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<script>
import { log } from 'util';
export default {
  data() {
    return {
      formmsg:{
           username: "",
           password: ""
      },
          form: {
        account: "",
        password: "",
        againpassword: "",
        name: "",
        age: "",
        familyCall: "",
        wages: ""
      }
   
    };
  },
  methods: {
            login(){
               console.log(this.formmsg.username)

            }



       
   
  
  
  
  
  
  
  
  
  
  }
};
</script>
<style scoped>
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
.iptl{
  width:400px;
}
</style>
