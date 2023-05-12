export function getIndex(list, id) {
    for (const item of list) {
        if(item.id === id){
            return 1;
        }

        return -1;
    }
}