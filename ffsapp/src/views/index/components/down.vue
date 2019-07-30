<template>
  <div>
    <!-- <button type="button" class="layui-btn">一个标准的按钮</button> -->
    <!-- <Table :row-class-name="rowClassName" :columns="columns1" :data="data1"></Table> -->
    <Row>
      <Col offset="6"  style="margin-top:150px">
        <el-upload class="upload-demo" drag action="http://localhost:8080/databast/sss" multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">
            将文件拖到此处，或
            <em>点击上传</em>
          </div>
          <div class="el-upload__tip" slot="tip">请上传备份文件，且不超过500kb</div>
        </el-upload>
      </Col>
    </Row>
  </div>
</template>
<script>
export default {  
  data() {
    return {
      columns1: [
        {
          title: "ID",
          key: "id"
        },
        {
          title: "收入",
          key: "quota"
        },
        {
          title: "类型",
          key: "type"
        },
        {
          title: "日期",
          key: "incomeDate"
        }
      ],

      data1: [
        {
          id: "John Brown",
          quota: 18,
          type: "New York No. 1 Lake Park",
          incomeDate: "2016-10-03"
        }
      ]
    };
  },
  methods: {}

  // methods:{

  // },
  // mounted(){
  //       this.$axios
  //     .get(" http://localhost:8080/ffs/income-info/byid/9", {})
  //     .then(res => {
  //       if (res.data.code == "200") {
  //         this.data1 = res.data.data
  //         // console.log(res.data);
  //         // for (var i = 0; i < res.data.data.length; i++) {
  //         //   var data = { value: "", name: "" };
  //         //   this.qu[i] = res.data.data[i].quota;
  //         //   this.ty[i] = res.data.data[i].type;
  //         //   // this.income[i] = res.data.data[i].incomeRoot;
  //         //   // console.log("收入方式     ：" + this.income[i]);
  //         //   this.qusum += parseInt(res.data.data[i].quota);
  //         //   data.name = res.data.data[i].incomeRoot;
  //         //   data.value = res.data.data[i].quota;
  //         //   this.inDX.push(data);
  //         // }

  //         // console.log("==收入总金===" + this.qusum);

  //         // console.log(this.qu);
  //         // console.log(this.ty);
  //         // console.log("=======");

  //       } else {
  //         console.log(res.data.data);
  //       }
  //     });
  // }

  // }
};
</script>

<style scoped>
</style>
