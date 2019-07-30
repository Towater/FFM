<template>
     <div>

        <Row  style="background:#515a6e" type="flex" justify="center" align="middle" class="code-row-bg">
              <Col span="2" offest="1" style="padding:5px;color:white;" >
               <span><h4>家庭管理系统</h4></span>
        </Col>
     
    

      
        <Col span="2" offset="9" style="padding-top:25px;color:white; margin-top:5px"><span ><h4>欢   迎  {{user}}</h4></span></Col>
        <Col  span="1" offset="9" style="color:white;margin-top:35px;">
            <router-link to="/login"><span>退出登录</span></router-link>        
        </Col>
    </Row>

    <!-- accordion -->

    <Row>
        <Col  span="5">
            <Menu active-name="3-2" :open-names="['1']" theme="dark"  width="200px" style="min-height:100vh">
        <Submenu name="1">
            <template slot="title">
                <Icon type="ios-analytics" />
                用户管理
            </template>
            <MenuGroup title="---用户---">
                <MenuItem name="1-1" to='/main/UIndex'>用户信息</MenuItem>
                <MenuItem name="1-2" to='/main/UChangePwd'>密码修改</MenuItem>
            </MenuGroup>
            <MenuGroup title="---管理员---">
        
                <MenuItem name="1-3" to="/main/adduser">信息编辑</MenuItem>
            </MenuGroup>
        </Submenu>
        <Submenu name="2">
            <template slot="title">
                <Icon type="ios-filing" />
                数据库维护
            </template>
            <MenuItem name="2-1" to="/main/datadown">数据备份 </MenuItem>
            <MenuItem name="2-2" to="/main/dataup">数据恢复 </MenuItem>
            <MenuItem name="2-3" to="/main/dataformat">数据格式化 </MenuItem>

   
        </Submenu>
        <Submenu name="3">
            <template slot="title">
                <Icon type="ios-cog" />
                报表分析
            </template>  
            <MenuItem name="3-1" to="/main/square">矩形</MenuItem>
        </Submenu>

        <Submenu name="4">
            <template slot="title">
                <Icon type="ios-cog" />
                收支管理
            </template>  
            <MenuItem name="4-1" to="/main/income">收入管理</MenuItem>
            <MenuItem name="4-2" to="/main/pay">支出管理</MenuItem>
        </Submenu>
        <Submenu name="5">
            <template slot="title">
                <Icon type="ios-cog" />
                投资管理
            </template>  
            <MenuItem name="5-1" to="/main/Gold">黄金</MenuItem>
            <MenuItem name="5-2" to="/main/Exchange">汇率</MenuItem>
            <MenuItem name="5-3" to="/main/Stock">股票</MenuItem>
        </Submenu>
    </Menu>
        </Col>

        <Col  span="19">
        <transition
        enter-active-class="animated zoomInUp"
        leave-active-class="animated zoomOutUp"
        :duration="250">
                        <router-view></router-view>

        </transition>
        </Col>
    </Row>

     </div>
</template>

<script>

export default {
  data() {
    return {
        user:'zkw'
    }
    
  },
 mounted () {
      this.user = window.localStorage.getItem('username')
    },
  methods: {
    //点击登录按钮
 
  }
}

</script>

<style scoped>
*{
    margin: 0;
    padding: 0;
}
/* span{   
  
     font-weight: bold;
    color: purple;
    border:1px solid  yellow;
    background: #cccccc;
    line-height: 50%;
    text-align: center; 
} */
.router-link-active {
  text-decoration: none;
}
a {
  text-decoration: none;
}

</style>

