<template>
  <div>
    <el-button type="primary" @click="down">下载</el-button>
    <el-row>
      <el-table :data="tableData" :label-width="formLabelWidth" class="ABC">
        <el-table-column prop="quota" label="金额" :label-width="formLabelWidth"></el-table-column>
        <el-table-column prop="type" label="收入方式" :label-width="formLabelWidth"></el-table-column>
        <el-table-column prop="incomeRoot" label="收入来源" :label-width="formLabelWidth"></el-table-column>
        <el-table-column prop="incomeDate" label="收入时间" :label-width="formLabelWidth"></el-table-column>
        <el-table-column prop="remakers" label="相关备注" :label-width="formLabelWidth"></el-table-column>
      </el-table>
    </el-row>
      
  </div>
</template>

<script>
import FileSaver from 'file-saver'
import XLSX from 'xlsx'
export default {
  data() {
    return {
      name: "DataDown",
      formLabelWidth: "120px",
      tableData: []
    };
  },
  methods:{
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
    down(){
      var tb=XLSX.utils.table_to_book(document.querySelector(".ABC"));
      var tbout=XLSX.write(tb,{
        bookType:"xlsx",
        bookSST:true,
        type:"array"
      });
      try{
        FileSaver.saveAs(
          new Blob([tbout],{type:"application/octet-strem"}),
          this.name +".xlsx"
        );
      }catch(e){
        if(typeof console !== "undefined") console.log(e,tbout)
      }
    },
    GetDataDown(){
      this.AuthConf();
      this.$axios({
        methods:"get",
        url:"/ffs/income-info/byid"
      }).then(res => {
        if (res.data.code == "200") {
            this.tableData =res.data.data
            console.log(res.data.data); 
        } else {
          console.log(res.data.data);
        }
      });
    }
  },
  mounted(){
    var that =this;
    this.GetDataDown();
  }
};
</script>

<style scoped>
</style>
