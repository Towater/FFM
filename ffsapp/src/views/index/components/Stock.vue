<template>
  <el-container style="border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu
        :default-openeds="['1', '1']"
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
      >
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-s-marketing"></i>
            <span>沪深</span>
          </template>
          <el-table :data="stockInfo" style="width: 100%"  @row-click="getDetail">
            <el-table-column prop="stockName" width="180"></el-table-column>
          </el-table>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header>
        <el-button type="primary" icon="el-icon-plus" @click="dialogFormVisible = true">关注股票</el-button>
        <el-dialog title="关注新股票" :visible.sync="dialogFormVisible" style="text-align:center">
          <el-form>
            <el-form-item label="股票代码" :label-width="formLabelWidth">
              <el-input v-model="stockCode" auto-complete="off"></el-input>
            </el-form-item>
          </el-form>
          <div>
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false;guanzhu()">确 定</el-button>
          </div>
        </el-dialog>

        <el-button type="primary" icon="el-icon-plus" @click="dialogFormVisible1 = true">取消关注</el-button>
        <el-dialog title="删除股票代码" :visible.sync="dialogFormVisible1" style="text-align:center">
          <el-form>
            <el-form-item label="股票代码" :label-width="formLabelWidth">
              <el-input v-model="stockCode" auto-complete="off"></el-input>
            </el-form-item>
          </el-form>
          <div>
            <el-button @click="dialogFormVisible1 = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible1 = false;cancelGuanzhu()">确 定</el-button>
          </div>
        </el-dialog>

        <!-- <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>

<el-dialog title="收货地址" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="活动名称" :label-width="formLabelWidth">
      <el-input v-model="form.name" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="活动区域" :label-width="formLabelWidth">
      <el-select v-model="form.region" placeholder="请选择活动区域">
        <el-option label="区域一" value="shanghai"></el-option>
        <el-option label="区域二" value="beijing"></el-option>
      </el-select>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
  </div>
        </el-dialog>-->
      </el-header>

      <h1 id="stockName">
        <span  class="c8_name" >{{stockDetail.data.name}}</span>
        <span>{{stockDetail.data.gid}}</span>
      </h1>

      <tbody>
        <tr>
          <th>今&nbsp;&nbsp;开：</th>
          <td class="@UD_open_color@">{{stockDetail.data.todayStartPri}}</td>
          <th>&nbsp;&nbsp;成交量：</th>
          <td>{{stockDetail.data.traNumber}}</td>
          <th>涨跌比：</th>
          <td>{{stockDetail.data.increPer}}</td>
        </tr>
        <tr>
          <th>最&nbsp;&nbsp;高：</th>
          <td class="@UD_high_color@">{{stockDetail.data.todayMax}}</td>
          <th>&nbsp;&nbsp;成交额：</th>
          <td>{{stockDetail.data.traAmount}}</td>
          <th>涨跌额：</th>
          <td>{{stockDetail.data.increase}}</td>
        </tr>
        <tr>
          <th>最&nbsp;&nbsp;低：</th>
          <td class="@UD_low_color@">{{stockDetail.data.todayMin}}</td>
          <th>&nbsp;&nbsp;竞买价：</th>
          <td>{{stockDetail.data.competitivePri}}</td>
          <th>竞卖价：</th>
          <td>{{stockDetail.data.reservePri}}</td>
        </tr>
        <tr>
          <th>昨&nbsp;&nbsp;收：</th>
          <td>{{stockDetail.data.yestodEndPri}}</td>
          <th>&nbsp;&nbsp;当前价：</th>
          <td>{{stockDetail.data.nowPri}}</td>
          <th>日&nbsp;&nbsp;期：</th>
          <td>{{stockDetail.data.date}}</td>
        </tr>
        <!-- <tr>
          <th>当前价：</th>
          <td>@zgb@</td>
          <th>流通股：</th>
          <td>@ltgb@</td>
        </tr>-->
      </tbody>

      <el-main>
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="分时" name="first">
            <el-image v-bind:src="stockDetail.gopicture.minurl">
              <div slot="placeholder" class="image-slot">
                加载中
                <span class="dot">...</span>
              </div>
            </el-image>
          </el-tab-pane>
          <el-tab-pane label="日K" name="second">
            <el-image v-bind:src="stockDetail.gopicture.dayurl">
              <div slot="placeholder" class="image-slot">
                加载中
                <span class="dot">...</span>
              </div>
            </el-image>
          </el-tab-pane>
          <el-tab-pane label="周K" name="third">
            <el-image v-bind:src="stockDetail.gopicture.weekurl">
              <div slot="placeholder" class="image-slot">
                加载中
                <span class="dot">...</span>
              </div>
            </el-image>
          </el-tab-pane>
          <el-tab-pane label="月K" name="fourth">
            <el-image v-bind:src="stockDetail.gopicture.monthurl">
              <div slot="placeholder" class="image-slot">
                加载中
                <span class="dot">...</span>
              </div>
            </el-image>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
export default {
  data() {
    return {
      activeName: "first",
      dialogTableVisible: false,
      dialogFormVisible: false,
      dialogTableVisible1: false,
      dialogFormVisible1: false,
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: ""
      },
      stockInfo: [],//股票信息
      formLabelWidth: "120px",
      stockCode: "",//输入的股票代码
      stockDetail:{}//股票详细信息
    };
  },
  mounted() {
    this.getStock();
  },
  methods: {
    getDetail(row){
      var that = this;
      this.AuthConf();
      this.$axios({
        url:"/ffs/stock/hs",
        method:"get",
        params:{
          code:row.stockCode
        }
      }).then(res=>{
        console.log(res.data);
        if(res.data.code=="200")
        {
          this.stockDetail = res.data.data[0];
        }
      })
    },

    getStock(){
      var that = this;
      this.AuthConf();
      this.$axios({
        url:"/ffs/stock/byID",
        method:"get",
      }).then(res=>{
        if(res.data.code=="200")
        {
          this.stockInfo = res.data.data;
          this.getDetail(this.stockInfo[0]);
          console.log(this.stockInfo);
        }
      })
    },
    cancelGuanzhu(){
      console.log(this.stockCode);
      var that = this;
      this.AuthConf();
      this.$axios({
        url: "/ffs/stock/del",
        method: "get",
        params: {
          stockCode: this.stockCode
        }
      }).then(res => {
        alert(res.data.data);
      });
    },
    guanzhu() {
      console.log(this.stockCode);
      var that = this;
      this.AuthConf();
      this.$axios({
        url: "/ffs/stock/add",
        method: "get",
        params: {
          stockCode: this.stockCode
        }
      }).then(res => {
        alert(res.data.data);
        this.stockCode = res.data.data;
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
  }
};
</script>

<style>
.el-header {
  text-align: right;
  line-height: 60px;
}

.el-aside {
  text-align: center;
}

.c8_name {
  font-style: normal;
  display: inline-block;
  max-width: 500px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  vertical-align: top;
}
#stockName {
  float: left;
  padding-left: 10px;
  background: url(//i0.sinaimg.cn/cj/realstock/2012/images/icon_left.png) 0 -109px
    no-repeat;
  font-size: 30px;
  font-family: 微软雅黑, 黑体;
  color: #012793;
}
.el-tabs {
  float: none;
}

th {
  font-style: normal;
  font-weight: normal;
}
.other {
  float: right;
  width: 320px;
  margin-top: 5px;
  color: #3d6cb0;
  line-height: 22px;
}
.other table {
  width: 100%;
}
.other table th {
  text-align: right;
  color: #062784;
  white-space: nowrap;
}
.other table td {
  white-space: nowrap;
}
</style>


