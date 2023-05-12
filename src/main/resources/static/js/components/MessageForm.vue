<template>
  <div v-on:keyup.enter="send">
    <input class="new-message" type = "text" v-model="text"/>
    <input class="button" type = "button" value="Send" @click="send"/>
  </div>
</template>

<script>
  import { sendMessage } from "../util/ws";

  export default {
    data() {
      return {
        text: ''
      }
    },
    methods: {
      send() {
        if(this.text === '')
          return

        let message = {
          username : this.username,
          text: this.text
        };

        sendMessage(message)
        this.text = ''
      }
    },
    props: ['messages', 'username']
  }
</script>

<style>
  .new-message{
    outline: none;
    border: none;
    width: 339px;
    height: 26px;
    background: #FFFFFF;
    border-radius: 10px;
    margin: 15px 12px 15px 18px;
  }
  .button{
    border: none;
    border-radius: 50%;
    width: 38px;
    height: 38px;
    font-size: 12px;
    background: rgba(150, 125, 110, 0.9);
    color: #4D433E;
  }
  .button:hover{
    color: papayawhip;
  }
</style>
