<template>
  <div>
    <!--用户查询 -->
    <el-input
      type="text"
      style="width:150px;margin-right:5px;"
      placeholder="请输入内容"
      prefix-icon="el-icon-search"
      v-model="input"
    ></el-input>
    <el-button type="text" id="addSearch">用户查询</el-button>

    <!-- 新增用户 -->
    <!-- // String account, String password, String name,int age, String familyCall, BigDecimal wages) -->
    <el-button type="text" @click="dialogFormVisible = true" id="addUser">新增用户</el-button>
    <el-dialog title="添加用户信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item class="iptl" label="账号" :label-width="formLabelWidth">
          <el-input v-model="form.account" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item class="iptl" label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.password" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item class="iptl" label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item class="iptl" label="年龄" :label-width="formLabelWidth">
          <el-input v-model="form.age" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item class="iptl" label="家庭称呼" :label-width="formLabelWidth">
          <el-input v-model="form.familyCall" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item class="iptl" label="工资" :label-width="formLabelWidth">
          <el-input v-model="form.wages" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="newAdd">确 定</el-button>
      </div>
    </el-dialog>
    <el-table :data="tableData" border style="width: 721px">
      <el-table-column prop="name" label="姓名" width="120"></el-table-column>
      <el-table-column prop="age" label="年龄" width="120"></el-table-column>
      <el-table-column prop="familyCall" label="家庭称呼" width="120"></el-table-column>
      <el-table-column prop="wages" label="工资" width="120"></el-table-column>
      <el-table-column prop="familyId" label="家庭ID" width="120"></el-table-column>

      <el-table-column label="操作" width="120" type="index">
        <template slot-scope="scope">
          <el-button
            @click="handleClick(scope.$index);dialogFormVisiblex = true"
            type="text"
            size="small"
          >修改</el-button>

          <el-dialog :visible.sync="dialogFormVisiblex">
            <el-form :model="form">
              <el-form-item class="iptl" label="账号" :label-width="formLabelWidth">
                <el-input v-model="form.account" auto-complete="off" ></el-input>
              </el-form-item>
              <el-form-item class="iptl" label="姓名" :label-width="formLabelWidth">
                <el-input v-model="form.name" auto-complete="off" ></el-input>
              </el-form-item>

              <el-form-item class="iptl" label="年龄" :label-width="formLabelWidth">
                <el-input v-model="form.age" auto-complete="off"></el-input>
              </el-form-item>

              <el-form-item class="iptl" label="家庭称呼" :label-width="formLabelWidth">
                <el-input v-model="form.familyCall" auto-complete="off" ></el-input>
              </el-form-item>
              <el-form-item class="iptl" label="工资" :label-width="formLabelWidth">
                <el-input v-model="form.wages" auto-complete="off" ></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="update(scope.$index)">确 定</el-button>
            </div>
          </el-dialog>
          <el-button type="text" size="small" @click="delUserInfo(scope.$index)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  mounted() {
    this.UserShow();
  },
  methods: {
    /**
     * 通过更新用户数据Id
     */
    update(index) {
      var that = this;
      that.AuthConf();
      this.$axios({
        method: "get",
        url: "/updateUser",
        params: {
          id: this.tableData[index].id,
          account: this.tableData[index].account,
          name:this.tableData[index].name,
          age: this.tableData[index].age,
          familyCall:this.tableData[index].familyCall,
          wages: this.tableData[index].wages
        }
      }).then(res => {
          if (res.data.code == "200") {
            this.dialogFormVisiblex = false;
            // console.log("修改成功")
            alert("修改成功");
          } else {
            // console.log("修改成功")
            alert("修改成功");
            this.dialogFormVisiblex = false;
          }
        });
    },

    /**
     * 新增用户
     * 仅管理员 */
    newAdd() {
      var that = this;
      that.AuthConf();
      // <!-- // String account, String password, String c,int age, String familyCall, BigDecimal wages) -->
      axios({
        method: "get",
        url: "/addUser",
        data: {
          account: this.form.account,
          password: this.form.password,
          name: this.form.name,
          age: this.form.age,
          familyCall: this.form.familyCall,
          wages: this.form.wages
        }
      }).then(res => {
        if (res.data.code == "200") {
          console.log("添加成功");
        } else if (res.data.code == "401") {
          localStorage.removeItem("token");
          this.$router.push("/login");
          console.log("401，用户未登录，跳转登录");
        } else if (res.data.code == "403") {
          this.$message({
            type: "info",
            message: "不是管理员，无权限进行此操作!"
          });
        }
      });
      this.dialogFormVisible = false;
    },
    handleClick(index) {
      this.form = this.tableData[index];
      // console.log(row);
    },
    /**
     * 通过id删除用户
     * 仅管理员
     */
    delUserInfo(index) {
      console.log(index);
      this.$confirm("是否删除该用户的所有信息", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
          this.$axios({
            method: "get",
            url: "/deleteUserById",
            params: {
              id: this.tableData[index].id
            }
          }).then(res => {
            if (res.data.code == "200") {
              console.log("删除成功");
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              this.$router.replace({path: '/main/adduser'})
            } else if (res.data.code == "401") {
              localStorage.removeItem("token");
              this.$router.push("/login");
              console.log("401，用户未登录，跳转登录");
            } else if (res.data.code == "403") {
              this.$message({
                type: "info",
                message: "不是管理员，无权限进行此操作!"
              });
            }
          });
        }).catch(() => {
          this.$message({
            type: "info",
            message: "删除已取消"
          });
        });
    },
    /**
     * 显示用户信息
     */
    UserShow() {
      var that = this;
      that.AuthConf();
      that
        .$axios({
          method: "get",
          url: "/selectFamiliesById"
        })
        .then(res => {
          if (res.data.code == "200") {
            console.log(res);
            this.tableData = res.data.data;
          } else if ((res.data.code = "401")) {
            localStorage.removeItem("token");
            this.$router.push("/login");
            console.log(this.tableData);
          } else if ((res.data.code = "404")) {
            //this.$router.push("/main/UIndex");
          }
        }).catch(res=>{
          //this.$router.push("/main/UIndex");
        });
    },
    /**请求拦截器 */
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
  data() {
    return {
      tableData: [],
      dialogTableVisible: false,
      dialogFormVisible: false,
      dialogTableVisiblex: false,
      dialogFormVisiblex: false,
      form: {
        account: "",
        name: "",
        age: "",
        familyCall: "",
        wages: ""
      },
      formLabelWidth: "120px",
      input: ""
    };
  }
};
</script>

<style scoped>
#addUser {
  margin: 40px;
}
.iptl {
  width: 400px;
}
</style>