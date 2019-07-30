<template>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%">
      <el-table-column
        prop="name"
        label="货币名称"
        width="90">
      </el-table-column>
      <el-table-column
        prop="fBuyPri"
        label="现汇买入价">
      </el-table-column>
      <el-table-column
        prop="mBuyPri"
        label="现钞买入价">
      </el-table-column>
      <el-table-column
        prop="fSellPri"
        label="现汇卖出价">
      </el-table-column>
      <el-table-column
        prop="mSellPri"
        label="现钞卖出价">
      </el-table-column>
      <el-table-column
        prop="bankConversionPri"
        label="银行折算价">
      </el-table-column>
      <el-table-column
        prop="date"
        label="发布日期">
      </el-table-column>
      <el-table-column
        prop="time"
        label="发布时间">
      </el-table-column>

    </el-table>
  </template>

  <script>
    export default {
      data() {
    return {
      tableData: []
    };
  },
  methods: {
    getInfo() {
      var that = this;
      this.AuthConf();
      this.$axios({
        url: "/ffs/stock/exchange",
        method: "get",
      }).then(res => {
        this.tableData.push(res.data.data[0].data1);
        this.tableData.push(res.data.data[0].data2);
        this.tableData.push(res.data.data[0].data3);
        this.tableData.push(res.data.data[0].data4);
        this.tableData.push(res.data.data[0].data5);
        this.tableData.push(res.data.data[0].data6);
        this.tableData.push(res.data.data[0].data7);
        this.tableData.push(res.data.data[0].data8);
        this.tableData.push(res.data.data[0].data9);
        this.tableData.push(res.data.data[0].data10);
        this.tableData.push(res.data.data[0].data11);
        this.tableData.push(res.data.data[0].data12);
        this.tableData.push(res.data.data[0].data13);
        this.tableData.push(res.data.data[0].data14);
        this.tableData.push(res.data.data[0].data15);
        this.tableData.push(res.data.data[0].data16);
        this.tableData.push(res.data.data[0].data17);
        this.tableData.push(res.data.data[0].data18);
        this.tableData.push(res.data.data[0].data19);
        this.tableData.push(res.data.data[0].data20);
        this.tableData.push(res.data.data[0].data21);
        console.log(this.tableData);
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
    this.getInfo();
  }
    }
  </script>