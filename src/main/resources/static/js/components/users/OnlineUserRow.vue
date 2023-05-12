<template>
  <div>
    <div :class="onlineUserInfo.username === username? you: other">
      <vue-horizontal>
        <div class="avatar">
        </div>

        <div class="user-info">
          {{onlineUserInfo.username}}
        </div>
         <div class="You" v-if="onlineUserInfo.username === username">You</div>

      </vue-horizontal>
    </div>
  </div>
</template>

<script>
import VueHorizontal from "vue-horizontal";
export default {
  components:{
    VueHorizontal
  },
  props: ["username", "onlineUserInfo"],
  data() {
    return {
      you: 'row-you',
      other: 'row-other',
      isMy: false
    }
  },
  created() {
    this.isMy = this.username === this.onlineUserInfo.username
  }
}


</script>

<style scoped>
  .user-info{
    margin-top: 19px;
    text-align: center;
    font-weight: 400;
    line-height: 15px;

    color: rgba(0, 0, 0, 0.75);
  }

  .avatar{
    margin-top: 10px;
    margin-left: 11px;
    margin-right: 16px;
    width: 36px;
    height: 36px;
    border-radius: 50%;
    background-color: white;
  }
  .row-other{
    width: 153px;
    height: 57px;

    margin-bottom: 5px;

    background: #BDA79D/*#CCBFB4*/;
    border-radius: 5px;
  }

  .row-you{
    width: 153px;
    height: 57px;

    margin-bottom: 5px;

    background: #BDA79D;
    border-radius: 5px;
  }

  .You {
    font-weight: 400;
    font-size: 12px;
    line-height: 15px;
    /* identical to box height */

    margin-top: 5px;
    margin-right: 4px;
    margin-left: auto;
    color: #967D6E;
  }
</style>