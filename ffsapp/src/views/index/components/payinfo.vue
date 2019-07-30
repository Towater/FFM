<template>
  <div>
    <div>
      <el-table
        :data="tableData"
        style="width: 80%"
        stripe
        border
        @row-dblclick="rightClick"
        v-loading.fullscreen.lock="isloading"
      >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="金额">
                <span>{{ props.row.quota }}</span>
              </el-form-item>
              <el-form-item label="支出类型">
                <span>{{ props.row.type }}</span>
              </el-form-item>
              <el-form-item label="用户ID">
                <span>{{ props.row.userid }}</span>
              </el-form-item>
              <el-form-item label="支出日期">
                <span>{{ props.row.payDate }}</span>
              </el-form-item>
              <el-form-item label="支出项">
                <span>{{ props.row.payFor }}</span>
              </el-form-item>
              <el-form-item label="备注">
                <span>{{ props.row.remakers }}</span>
              </el-form-item>
              <el-form-item>
                <el-tooltip content="编辑这条数据" placement="bottom" effect="dark">
                  <el-button
                    type="success"
                    icon="el-icon-edit"
                    circle
                    size="mini"
                    @click="showUpdateForm(props.row)"
                  ></el-button>
                </el-tooltip>&nbsp;&nbsp;&nbsp;
                <el-popover placement="top" width="160" v-model="visible">
                  <p style="font-size:13px">确定删除该条数据吗？</p>
                  <div style="text-align: right; margin: 0 ">
                    <el-button size="mini" type="text" @click="visible = false">取消</el-button>
                    <el-button type="primary" size="mini" @click="del(props.row.id)">确定</el-button>
                  </div>
                  <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    size="mini"
                    slot="reference"
                  ></el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="payDate" label="支出日期" width="180" sortable="true"></el-table-column>
        <el-table-column prop="quota" label="金额" sortable="true"></el-table-column>
        <el-table-column prop="payFor" label="支出源"></el-table-column>

        <el-table-column align="right" prop="type">
          <template slot="header" slot-scope="scope">
            <!-- 这里是搜索和新增btn -->
            <el-button
              v-show="addvisiable"
              size="mini"
              icon="el-icon-circle-plus-outline"
              round
              style="margin:auto"
              @click="addinfoshow"
            ></el-button>
            <el-input
              prefix-icon="el-icon-search"
              size="mini"
              maxlength="5"
              :disabled="false"
              :autosize="true"
              placeholder="输入搜索"
            />
          </template>
        </el-table-column>
      </el-table>

      <div id="page">
        <el-pagination
          background
          layout="prev, pager, next"
          :page-count="total"
          @current-change="handlePageChange"
          :current-page.sync="currentPage"
        ></el-pagination>
      </div>
    </div>

<!-- update表单 -->
    <div>
      <el-dialog title="修改数据" :visible.sync="updateFormVisible">
        <el-form :model="form">
          <el-form-item
            label="支出日期"
            :label-width="formLabelWidth"
            style="width:80%"
            label-position="labelPosition"
          >
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="temp.payDate"
              style="width: 100%;"
              format="yyyy/MM/dd"
              value-format="yyyy/MM/dd"
            ></el-date-picker>
          </el-form-item>
          <el-form-item
            label="金额"
            :label-width="formLabelWidth"
            style="width:80%"
            label-position="labelPosition"
          >
            <el-input v-model.number="temp.quota" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item
            label="支出源"
            :label-width="formLabelWidth"
            style="width:80%"
            label-position="labelPosition"
          >
            <el-input v-model="temp.payFor" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item
            label="支出类型"
            :label-width="formLabelWidth"
            style="width:80%"
            label-position="labelPosition"
          >
            <el-select v-model="temp.type" placeholder="请选择收入类型">
              <el-option label="现金" value="现金"></el-option>
              <el-option label="微信" value="微信"></el-option>
              <el-option label="支付宝" value="支付宝"></el-option>
              <el-option label="银行卡" value="银行卡"></el-option>
              <el-option label="其他" value="其他"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="备注"
            :label-width="formLabelWidth"
            style="width:80%"
            label-position="labelPosition"
          >
            <el-input v-model="temp.remakers" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="updateFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="update">确 定</el-button>
        </div>
      </el-dialog>
    </div>

  <!-- 新增表单 -->
    <div>
      <el-dialog title="新增数据" :visible.sync="addFormVisible">
         <el-form :model="form">
          <el-form-item
            label="收入日期"
            :label-width="formLabelWidth"
            style="width:80%"
            label-position="labelPosition"
          >
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="temp1.payDate"
              style="width: 100%;"
              format="yyyy/MM/dd"
              value-format="yyyy/MM/dd"
            ></el-date-picker>
          </el-form-item>
          <el-form-item
            label="金额"
            :label-width="formLabelWidth"
            style="width:80%"
            label-position="labelPosition"
          >
            <el-input v-model.number="temp1.quota" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item
            label="收入源"
            :label-width="formLabelWidth"
            style="width:80%"
            label-position="labelPosition"
          >
            <el-input v-model="temp1.payFor" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item
            label="支出类型"
            :label-width="formLabelWidth"
            style="width:80%"
            label-position="labelPosition"
          >
            <el-select v-model="temp1.type" placeholder="请选择收入类型">
              <el-option label="现金" value="现金"></el-option>
              <el-option label="微信" value="微信"></el-option>
              <el-option label="支付宝" value="支付宝"></el-option>
              <el-option label="银行卡" value="银行卡"></el-option>
              <el-option label="其他" value="其他"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="备注"
            :label-width="formLabelWidth"
            style="width:80%"
            label-position="labelPosition"
          >
            <el-input v-model="temp1.remakers" type="textarea" maxlength="30" show-word-limit autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addinfoshow">取 消</el-button>
          <el-button type="primary" @click="addinfo">确 定</el-button>
        </div>
        
      </el-dialog>
    </div>
  </div>
</template>

<script>
import addInfoVue from './addInfo.vue';
import { userInfo } from 'os';
export default {
  data() {
    return {
      updateFormVisible: false,
      addFormVisible: false,
      total: 0,
      addvisiable: false,
      isloading: false,
      currentPage: 1,
      labelPosition: "left",
      dataInfo: {
        id: "",
        userid: "",
        quota: "",
        payDate: "",
        payFor: "",
        type: "",
        remakers: ""
      },
      tableData: [
        {
          userid: "没连上数据库",
          quota: "256",
          payDate: "2019/09/01",
          payFor: "打工",
          type: "现金",
          remakers: "404?403"
        }
      ],
      temp: {
        id: "",
        userid: "",
        quota: "",
        payDate: "",
        payFor: "",
        type: "",
        remakers: ""
      },
      temp1: {
        id: "1",
        userid: "",
        quota: "",
        payDate: "",
        payFor: "",
        type: "",
        remakers: ""
      },

      formLabelWidth: "120px",
      form: {},
      visible: false
    };
  },
  created() {
    this.getAllInfos(this.currentPage);
  },
  methods: {
    show() {
      this.updateFormVisible = !this.updateFormVisible;
    },
    // 主方法
    getAllInfos(currentPage) {
      this.AuthConf()
      this.isloading = !this.isloading;
      try {
        this.$axios.get("/ffs/pay-info/idLimit",{params:{page:currentPage}}).then(result => {
          console.log(result)
          this.tableData = result["data"]["data"];

          this.$axios.get("/ffs/pay-info/s/idPage").then(num => {
            this.total = num["data"];
          });
        });
      } catch {
        this.$notify.error({
          title: "错误",
          message: "发生错误"
        });
      } finally {
        this.isloading = !this.isloading;
      }
    },
    rightClick() {
      this.addvisiable = !this.addvisiable;
    },
    handlePageChange() {
      this.getAllInfos(this.currentPage);
      console.log(this.currentPage);
    },
    showUpdateForm(obj) {
      this.show();
      this.temp = obj;
    },
    update() {
      this.$axios.get("/ffs/pay-info/update", {params:this.temp}).then(result => {
        if (result.data.code == 200) {
          this.show();
          this.$notify({
            title: "成功",
            message: "数据更新成功",
            type: "success"
          });
          this.getAllInfos(this.currentPage);
        } else {
          this.show();
          this.$notify.error({
            title: "错误",
            message: "数据未能更新"
          });
        }
      });
    },
    del(id) {
      this.AuthConf()
      console.log(id)
      this.$axios.get("/ffs/pay-info/del",{params:{id:id}}).then(result => {
        if (result.data.code == 200) {
          this.$notify({
            title: "成功",
            message: result.data.data,
            type: "success"
          });
          this.visible = !this.visible;
          this.getAllInfos(this.currentPage);
        } else {
          this.visible = !this.visible;
          this.$notify.error({
            title: "错误",
            message: "数据未能删除"
          });
        }
      });
    },
    addinfoshow() {
      this.addFormVisible = !this.addFormVisible;
    },
    tempClean(){
      this.temp1={
        id: 1,
        quota: "",
        payDate: "",
        payFor: "",
        type: "",
        remakers: ""
      }
    },
    addinfo(){
      var that = this ;
      console.log(this.temp1)
      this.AuthConf();
      this.$axios({
        method:"post",
        url:"/ffs/pay-info/add",
        params:{
        quota:that.temp1.quota,
        payDate:that.temp1.payDate,
        type:that.temp1.type,
        payFor:that.temp1.payFor,
        remakers:that.temp1.remakers
      }
      }).then(result=>{        
        if(result.data.code == 200){
          this.$notify({
            title: "成功",
            message: "数据创建成功",
            type: "success"
          });
          this.addinfoshow()
          this.tempClean()
          this.getAllInfos(this.currentPage);
        }
        else{
          this.tempClean()
          this.$notify.error({
            title: "错误",
            message: "数据未能创建"
          });
          this.addinfoshow()
        }
          
      })
    },

    /**请求拦截器 */
    AuthConf() {
      var that = this;
      console.log("执行")
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
  
  }
};
</script>

<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
#page {
  text-align: center;
  margin-top: 100px;
}
</style>