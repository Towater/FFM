# lastff

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your tests
```
npm run test
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).




         logins(name) {
                    username= this.formItem.username
                    password=this.formItem.password
                     var that = this
                      console.log(username+password)
            
                that.$http.post('http://localhost:8080/login',
                    {
                        username: that.formItem.username,
                        password:that.formItem.password
                    },
                    {
                        emulateJSON:true
                    }).then(function(res){
                                console.log("状态吗"+res.status)
                               
                                if(res.status ==="200")
                                {
                                    window.localStorage.setItem('userna', res.data.loginUser.rid)
                                    window.localStorage.setItem('condi', res.data.loginUser.condi)
                                    if(res.data.condi===2){
                                        this.$router.replace({path: '/manage'})
                                    }
                                    else if(res.data.condi===1){
                                        this.$router.replace({path: '/main'})
                                    }else{
                                        this.$router.replace({path: '/login'})
                                    }
                                }else{
                                    this.$router.replace({path: '/login'})
                                }
                            },function(res){
                                console.log(res.status);
                            });
            },