<template>
    <div class="login">
        <div class="background">
            <img :src="imgSrc" width="100%" height="100%" alt="" />
        </div>
        <div class="login-form">
            <div class="login-form-header">
                <img style="width: 75px; height: 75px;float: left;padding-right: 40px;" src="../assets/img/logo.png"
                    alt="" />
                <div class="login-form-text">电影售票系统 - 登录</div>
            </div>
            <div style="color: #000000;font-weight: bolder">
                <p style="font-size: 20px">用户名</p>
                <el-input class="login-form-input" v-model="username" placeholder="账 号"></el-input>
                <p style="font-size: 20px">密码</p>
                <el-input class="login-form-input" placeholder="密 码" v-model="password" show-password></el-input>
                <div style="padding-top: 10px">
                    <el-checkbox v-model="remember">记住密码</el-checkbox>
                    <div style="float: right">
                        <el-link href="/forget" style="font-weight: bolder;font-size: 14px;color: #000000;"
                            :underline="false">
                            忘记密码?
                        </el-link>
                    </div>
                </div>
                <el-button @click="postLogin" class="login-form-button" type="primary">登录</el-button>
            </div>
            <div class="login-form-footer">
                <el-link href="/register" style="font-weight: bolder;font-size: 16px;color: #000000;"
                    :underline="false">
                    还没有账号？去注册
                    <i style="font-weight: bolder;font-size: 15px" class="el-icon-right"></i>
                </el-link>
            </div>
        </div>
    </div>
</template>

<script>

import { Login } from "../api/user"
import md5 from "js-md5"
    
export default {
    data() {
        return {
            username: '',
            password: '',
            remember: false,
            imgSrc: require('../assets/img/bg.png')
        }
    },
    methods: {
        postLogin() {
            const LoginData = {
                username: this.username,
                password: md5(this.password),
                remember: this.remember
            };
            console.log(LoginData);
            Login(LoginData).then(res => {
                localStorage.setItem("uid", res.data.user.id);
                localStorage.setItem("token", res.data.token);
                this.$router.push({ path: '/', replace: true })
            })
        }
    }
}
</script>

<style scoped>
.background {
    width: 100%;
    height: 100%;
    /**宽高100%是为了图片铺满屏幕 */
    z-index: -1;
    position: absolute;
}

.login {
    width: 100%;
    height: 100%;
}

.login-form {
    width: 500px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -60%);
    letter-spacing: 2px;
}

.login-form-header {
    height: 20px;
    padding-left: 45px;
    padding-bottom: 100px;
}

.login-form-text {
    color: #000000;
    font-weight: bold;
    font-size: 30px;
    padding-top: 15px;
}

.login-form-input {
    margin-bottom: 10px;
}

.login-form-button {
    border-radius: 3px;
    width: 100%;
    font-weight: 600;
    font-size: 15px;
    letter-spacing: 2px;
    height: 60px;
    background: #5a84fd;
    box-shadow: 0 5px 30px rgb(0 66 8.5%);
    margin-top: 35px;
}

.login-form-footer {
    font-weight: bolder;
    color: #000000;
    padding-top: 40px;
    text-align: center;
}

>>>.el-input__inner {
    height: 48px;
}

.el-checkbox {
    color: #000000;
    font-weight: bolder;
    font-size: 15px;
}
</style>
