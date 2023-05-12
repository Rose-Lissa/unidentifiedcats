
<template>
  <div class="list">
    <div class="users">
      Users
    </div>
    <div class="rows">
      <online-user-row v-for="onlineUserInfo in onlineUsersInfo" :onlineUserInfo="onlineUserInfo" :username="username"/>
    </div>
    <div class="sign-out">
      <form action="/logout" method="post" class="form-signout">
        <input type="submit" value="Sign Out" class="button"/>
      </form>
    </div>
  </div>
</template>

<script>
import OnlineUserRow from "components/users/OnlineUserRow.vue"

export default {
  components: {OnlineUserRow},
  props: ["onlineUsersInfo", "username"]
}
</script>

<style scoped>
  .users{
    width: 162px;
    height: 34px;
    padding-top: 3px;
    padding-bottom: 10px;
    text-align: center;
    color: white;
  }
  .list{
    width: 162px;
    height: 480px;
    background: rgba(150, 125, 110, 0.9);

    padding-left: 4px;
  }

  .rows{
    width: 162px;
    height: 400px;
    overflow: hidden;
    overflow-y: scroll;
  }

  .button{
    width: 94px;
    height: 26px;

    margin-left: 29px;
    margin-bottom: 15px;

    background: #BDA79D;
    border: 1px solid rgba(0, 0, 0, 0.3);
    border-radius: 10px;
  }

  .rows::-webkit-scrollbar{
    width: 0;
  }
</style>