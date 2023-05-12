<template>
  <div class="row">
    <div class="sender">{{message.username}}</div>
    <div class="message-place">
      <div class="message">
        {{ message.text }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['message']
}
</script>

<style scoped>
  .sender{
    color: #4D433E;
  }
  .row{
    margin-top: 5px;
    margin-bottom: 5px;
  }
  .message-place{
    background-color: white;
    border-radius: 5px;
    display: inline-block;
    padding: 3px 7px;
  }
  .message{

  }
</style>

