new Vue({
    el: '#app',
    data: function() {
        return {
            userSearchForm: {
                userName: '',
            },
            userTableColumns: [
                {title: '用户名称', key: 'name'},
                {title: '年龄', key: 'age',width: 200,},
                {title: '手机号码', key: 'phone'},
                {title: '归属部门', key: 'dept'},
                {title: '地址', key: 'address'},
                {
                    title: '操作',
                    key: 'action',
                    fixed: 'right',
                    width: 350,
                    render: (h, params) => {
                        return h('div', [
                            h('Button', {
                                props: { type: 'primary', ghost: true,  },
                                style: { marginRight: '10px' },
                                on: { click: () => { this.editHandle(params.row) } }
                            }, '编辑'),
                            h('Button', {
                                props: { type: 'warning', ghost: true, },
                                style: { marginRight: '10px' },
                            }, '查看'),
                            h('Button', {
                                props: { type: 'error', ghost: true,  },
                                style: { marginRight: '10px' },
                            }, '删除'),
                            h('Button', {
                                props: { },
                                style: { marginRight: '10px' },
                            }, '密码重置')
                        ]);
                    }
                }
            ],
            userTableData: [
                {id: 'id1', name: 'John Brown', age: 18,phone:'13333333333', dept: '研发中心',address: 'New York No. 1 Lake Park', date: '2016-10-03'},
                {id: 'id1', name: 'Jim Green', age: 24, phone:'13333333333', dept: '研发中心',address: 'London No. 1 Lake Park', date: '2016-10-01'},
                {id: 'id1', name: 'John Brown', age: 18, phone:'13333333333', dept: '研发中心',address: 'New York No. 1 Lake Park', date: '2016-10-03'},
                {id: 'id1', name: 'Jim Green', age: 24, phone:'13333333333', dept: '研发中心',address: 'London No. 1 Lake Park', date: '2016-10-01'},
                {id: 'id1', name: 'John Brown', age: 18, phone:'13333333333', dept: '研发中心',address: 'New York No. 1 Lake Park', date: '2016-10-03'},
                {id: 'id1', name: 'John Brown', age: 18,phone:'13333333333', dept: '研发中心',address: 'New York No. 1 Lake Park', date: '2016-10-03'},
                {id: 'id1', name: 'Jim Green', age: 24, phone:'13333333333', dept: '研发中心',address: 'London No. 1 Lake Park', date: '2016-10-01'},
                {id: 'id1', name: 'John Brown', age: 18, phone:'13333333333', dept: '研发中心',address: 'New York No. 1 Lake Park', date: '2016-10-03'},
                {id: 'id1', name: 'Jim Green', age: 24, phone:'13333333333', dept: '研发中心',address: 'London No. 1 Lake Park', date: '2016-10-01'},
                {id: 'id1', name: 'John Brown', age: 18, phone:'13333333333', dept: '研发中心',address: 'New York No. 1 Lake Park', date: '2016-10-03'},
                {id: 'id1', name: 'Jim Green', age: 24, phone:'13333333333', dept: '研发中心',address: 'London No. 1 Lake Park', date: '2016-10-01'},
                {id: 'id1', name: 'John Brown', age: 18, phone:'13333333333', dept: '研发中心',address: 'New York No. 1 Lake Park', date: '2016-10-03'},
                {id: 'id1', name: 'Jim Green', age: 24, phone:'13333333333', dept: '研发中心',address: 'London No. 1 Lake Park', date: '2016-10-01'},
                {id: 'id1', name: 'John Brown', age: 18, phone:'13333333333', dept: '研发中心',address: 'New York No. 1 Lake Park', date: '2016-10-03'}
            ],
            userTableHeight: 200,
            isShow: false,
        }
    },
    computed: {

    },
    mounted() {
        this.userTableHeight = document.body.clientHeight - 110;
    },
    created() {

    },
    methods: {
        editHandle(params){
            console.info(params);

            this.isShow = true;
        },
    },
})