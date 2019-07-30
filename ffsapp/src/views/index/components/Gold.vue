<template>
  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="variety" label="品种" width="90"></el-table-column>
    <el-table-column prop="latestpri" label="最新价" width="90"></el-table-column>
    <el-table-column prop="openpri" label="开盘价" width="90"></el-table-column>
    <el-table-column prop="maxpri" label="最高价" width="90"></el-table-column>
    <el-table-column prop="minpri" label="最低价" width="90"></el-table-column>
    <el-table-column prop="limit" label="涨跌幅" width="90"></el-table-column>
    <el-table-column prop="yespri" label="昨收价" width="90"></el-table-column>
    <el-table-column prop="totalvol" label="总成交量" width="100"></el-table-column>
    <el-table-column prop="time" label="更新时间" width="200"></el-table-column>
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
        url: "/ffs/stock/gold",
        method: "get",
      }).then(res => {
        var temp = [];
        for(var i=1;i<=16;i++){
          this.tableData.push(res.data.data[0][i]);
        }
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
};
</script>