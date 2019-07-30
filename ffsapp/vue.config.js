module.exports = {
    pages: {
        index: {
            // 应用入口配置，相当于单页面应用的main.js，必需项
            entry: 'src/views/index/index.js',
            // 标题，可选项，一般情况不使用，通常是在路由切换时设置title
            // 需要注意的是使用title属性template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
            title: 'index',
        },
        manage: {
            entry: 'src/views/manage/manage.js',
            title: 'manage'
        }
    },
    devServer:{
        proxy: {
            '/api': {
                target:'http://localhost:8080',
                changeOrigin:true, 
                pathRewrite:{ 
                    '^/api': '/'
                }
            }
        }
    }
}