<template>
  <div class="container">
    <div v-if="isOnline">
      <vue-horizontal>
        <online-user-list :username="username" :onlineUsersInfo="onlineUsersInfo"/>
        <message-list :messages="messages" :username="username"/>
      </vue-horizontal>
    </div>
    <div v-else>
      Sorry, server is not available
    </div>
  </div>
</template>

<script>
  import VueHorizontal from 'vue-horizontal'
  import MessageList from 'components/MessageList.vue';
  import Axios from 'axios'
  import {
    addMessageHandler,
    setActionBeforeConnect,
    addOnlineUsersInfoHandler,
    connect,
    requestListOnlineUsers,
    setActionIfServerIsNotAvailable
  } from "util/ws";
  import { getIndex } from "util/collections"
  import OnlineUserList from "components/users/OnlineUserList.vue";

  export default {
    components:{
      VueHorizontal,
      OnlineUserList,
      MessageList
    },
    data() {
      return {
        isOnline: true,
        messages: [],
        username: userInfo.username,
        onlineUsersInfo: [
        ]
      }
    },
    created(){
      connect()

      setActionIfServerIsNotAvailable(() => {
        this.isOnline = false;
        this.messages.length = 0;
        this.onlineUsersInfo.length = 0;
      })

      setActionBeforeConnect(() => {
        this.isOnline = true

        Axios.get('/online').then( response =>
            response.data.forEach(userInfo => {
              this.onlineUsersInfo.push(userInfo)
            })
        )

        Axios.get('/message')
            .then(response =>
                response.data
                    .sort((a, b) => {return a.time - b.time})
                    .forEach(message => {
                      this.messages.push(message)
                      console.log(message.time)
                    }));
      })

      addMessageHandler(data => {
        console.log(data)
        data.forEach(message => {
          console.log(message)
          this.messages.push(message);
          this.messages.sort((a, b) => {
            return a.time - b.time
          })
        })
      })
      addOnlineUsersInfoHandler(data => {
        console.log(data)
        this.onlineUsersInfo.length = 0;
        data.forEach(userInfo => {
          console.log(userInfo)
          this.onlineUsersInfo.push(userInfo);
          })
      })



      console.log('username: ' + this.username);
    }
  }
</script>

<style>
  .container{
    display: flex;
    justify-content: center;
  }
</style>