<template>
  <div class="list">
    <div class="messages" id="messages">
      <message-row  v-for="message in messages" :message="message"/>
    </div>

    <message-form :messages="messages" :username="username" />
  </div>
</template>

<script>
  import MessageRow from 'components/MessageRow.vue'
  import MessageForm from 'components/MessageForm.vue';

  export default {
    components:{
      MessageRow,
      MessageForm
    },
    props: ['messages', 'username'],

    updated() {
      let block = document.getElementById("messages");
      block.scrollTop = block.scrollHeight;
    }
  }
</script>

<style scoped>
  .list{
    background-color: #CCBFB4;
  }
  .messages{
    width: 418px;
    height: 420px;
    overflow: hidden;
    overflow-y: scroll;
    padding-right: 25px;
    padding-left: 25px;
  }
  .messages::-webkit-scrollbar {
    width: 0;
  }
</style>