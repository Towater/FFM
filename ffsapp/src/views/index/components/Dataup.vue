<template>
  <div>
    <Row>
      <Col offset="6" style="margin-top:150px">
        <el-upload
          class="upload-demo"
          drag
          action="http://localhost:8080/databast/pullAllDataByFile"
          multiple
        >
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
  data: {
    return: {
      url: "http://localhost:8080/databast/pullAllDataByFile"
    }
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
};
</script>

<style scoped>
</style>
