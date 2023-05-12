let messageApi = Vue.resource('/message');

Vue.component('message-form', {
    props: ['messages'],
    data: function () {
        return {
            text: ''
        }
    },
    template: '<div>' +
        '<input type = "text" v-model="text"/>' +
        '<input type = "button" value="Send" @click="send"/>' +
        '</div>',
    methods: {
        send: function () {
            let message = { text: this.text};
            messageApi.save({}, message).then(result =>
                result.json().then(data => {
                    this.messages.push(data)
                    this.messages.sort((a, b) => {return a.time - b.time})
                })
            )
            this.text = ''
        }
    }
})

Vue.component('message-row', {
    props: ['message'],
    template: '<div>' +
        '<i>({{message.username}})</i> {{ message.text }}' +
        '</div>'
});

Vue.component('messages-list', {
    props: ['messages'],
    template: '<div>' +
        '<message-form :messages="messages" />'+
        '<message-row v-for="message in messages" :message="message"/>' +
        '</div>',
});

let app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages"/>',
    data: {
        messages: [],
        username: userInfo.username
    },
    created: function () {
        messageApi.get().then(result =>
            result.json().then(data =>
                data.sort((a, b) => {return a.time - b.time}).forEach(message => {this.messages.push(message)
                    console.log(message.time)
                })
            )
        );
        console.log('username: ' + this.username)
    }
});