Vue.use(Vuex);

var store = new Vuex.Store({
    modules: {
        common :{
            state: {
                website: "123123",
            },
            mutations: {

            }
        },
    },
    getters :{
        website: state => state.common.website,
    },
});

