import SockJs from 'sockjs-client'
import { Stomp } from "@stomp/stompjs"

let stompClient = null
let reconnectInterval = null
const messageHandlers = []
const onlineUsersInfoHandlers = []
let actionBeforeConnect = null
let actionIfServerIsNotAvailable = null;

export function connect() {
   let socket;

    clearInterval(reconnectInterval);

    stompClient = Stomp.over(() => {
        return socket = new SockJs('/message-websocket');
    });

    stompClient.connect({}, frame => {
        console.log("frame.data: " + frame);
        actionBeforeConnect()
        stompClient.subscribe('/topic/activity', message => {
            messageHandlers
                .forEach(handler => handler(JSON.parse(message.body)))
        });
        stompClient.subscribe('/topic/online', onlineUsersInfo => {
            onlineUsersInfoHandlers
                .forEach(handler => handler(JSON.parse(onlineUsersInfo.body)));
            console.log("/topic/online"+ onlineUsersInfo)
        });
        socket.onclose = () => {
            actionIfServerIsNotAvailable();
            // reconnectInterval = setInterval(function () {
            //     connect();
            // }, 2000);
        }
    })
}

export function setActionIfServerIsNotAvailable(action){
    actionIfServerIsNotAvailable = action;
}

export function setActionBeforeConnect(action){
    actionBeforeConnect = action
}

export function addMessageHandler(handler) {
    messageHandlers.push(handler)
}

export function addOnlineUsersInfoHandler(handler) {
    onlineUsersInfoHandlers.push(handler)
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

export function sendMessage(message) {
    console.log(message)
    stompClient.send("/app/send", {}, JSON.stringify(message))
}

export function requestListOnlineUsers(){
    stompClient.send("/app/online", {}, JSON.stringify("online"))
}