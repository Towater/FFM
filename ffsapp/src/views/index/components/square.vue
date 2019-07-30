<template>
  <div>
    <Row>
      <Col span="6">
        <div id="main" style="width: 600px;height:400px"></div>
      </Col>
      <Col span="6" offset="3">
        <div id="main2" style="width: 450px;height:400px"></div>
      </Col>
      <Col span="3" offset="2">
        <Card style="width:320px">
          <div style="text-align:center">
            <img
              src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2703782244,3399456763&fm=26&gp=0.jpg"
            />
            <h3>{{msg}} {{dif}}</h3>
          </div>
        </Card>
      </Col>
    </Row>
    <Row>
      <Col span="6">
        <div ref="charts" style="width:500px;height:500px;margin-top:30px"></div>
      </Col>

      <Col span="6" offset="3">
        <div ref="charts2" style="width:500px;height:500px;margin-top:30px"></div>
      </Col>
    </Row>
  </div>
</template>
<script>
var echarts = require("echarts");
export default {
  data() {
    return {
      qu: [],
      ty: [],
      qu2: [],
      ty2: [],
      income: [],

      payfor: [],
      inDX: [],
      outDX: [],
      qusum: 0,
      qusum2: 0,
      msg: "",
      dif: 0
    };
  },
  mounted() {
    var that = this;
    this.AuthConf();
    this.$axios({
      method: "get",
      url: "/ffs/income-info/byid"
    }).then(res => {
      if (res.data.code == "200") {
        // console.log(res.data);
        for (var i = 0; i < res.data.data.length; i++) {
          var data = { value: "", name: "" };
          this.qu[i] = res.data.data[i].quota;
          this.ty[i] = res.data.data[i].type;
          // this.income[i] = res.data.data[i].incomeRoot;
          // console.log("收入方式     ：" + this.income[i]);
          this.qusum += parseInt(res.data.data[i].quota);
          data.name = res.data.data[i].incomeRoot;
          data.value = res.data.data[i].quota;
          this.inDX.push(data);
        }
        this.getInCircle();
        // console.log("==收入总金===" + this.qusum);

        // console.log(this.qu);
        // console.log(this.ty);
        // console.log("=======");
        this.getInSquare();
      } else {
        console.log(res.data.data);
      }
    });


    this.$axios({
      method: "get",
      url: "/ffs/pay-info/byid"
    }).then(res => {
        if (res.data.code == "200") {
          console.log(res.data);
          for (var i = 0; i < res.data.data.length; i++) {
            this.qu2[i] = res.data.data[i].quota;
            this.ty2[i] = res.data.data[i].type;
            this.qusum2 += parseInt(res.data.data[i].quota);

            var data = { value: "", name: "" };

            data.name = res.data.data[i].payFor;
            data.value = res.data.data[i].quota;
            this.outDX.push(data);
            // console.log(this.outDX)
          }
          // console.log("****支出总资金 ：******" + this.qusum2);
          // console.log(this.qu2);
          // console.log(this.ty2);
          // console.log("**********");
          // option.series.data=data
          // 使用刚指定的配置项和数据显示图表。
          this.getOutSquare();
          this.getOutCircle();
          if (this.qusum > this.qusum2) {
            this.dif = this.qusum - this.qusum2;
            // console.log("chazhi " + this.dif);
            this.msg = "净收入";
          } else {
            this.dif = this.qusum - this.qusum2;
            // console.log("chazhi " + this.dif);
            this.msg = "净收入告急";
          }
        } else {
          console.log(res);
        }
    });
    
  },
  methods: {
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
    getInSquare() {
      var myChart = echarts.init(document.getElementById("main"));
      var option = {
        title: {
          text: "收入"
        },
        tooltip: {},
        legend: {
          data: ["单位/元"]
        },
        xAxis: {
          data: this.ty
        },
        yAxis: {},
        series: [
          {
            name: "单位/元",
            type: "bar",
            data: this.qu
          }
        ]
      };
      myChart.setOption(option);
    },
    getInCircle() {
      var myChart = echarts.init(this.$refs.charts);

      myChart.setOption({
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: "55%",
            itemStyle: {
              normal: {
                // 阴影的大小
                shadowBlur: 0,
                // 阴影水平方向上的偏移
                shadowOffsetX: 0,
                // 阴影垂直方向上的偏移
                shadowOffsetY: 0,
                // 阴影颜色
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            },
            data: this.inDX
          }
        ]
      });
    },
    getOutSquare() {
      var myChart2 = echarts.init(document.getElementById("main2"));
      var option2 = {
        color: "#6699cc",
        title: {
          text: "支出"
        },
        tooltip: {},
        legend: {
          data: ["单位/元"]
        },
        xAxis: {
          data: this.ty2
        },
        yAxis: {},
        series: [
          {
            name: "单位/元",
            type: "bar",
            data: this.qu2
          }
        ]
      };
      myChart2.setOption(option2);
    },
    getOutCircle() {
      var myChart2 = echarts.init(this.$refs.charts2);
      // 圆2
      myChart2.setOption({
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: "55%",
            itemStyle: {
              normal: {
                // 阴影的大小
                shadowBlur: 0,
                // 阴影水平方向上的偏移
                shadowOffsetX: 0,
                // 阴影垂直方向上的偏移
                shadowOffsetY: 0,
                // 阴影颜色
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            },
            data: this.outDX
          }
        ]
      });
    }
  }
};
</script>
<style scoped>
img {
  width: 150px;
  height: 80px;
}
</style>
