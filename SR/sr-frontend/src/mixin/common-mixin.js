export default {
  methods: {
    clean: function(object) {
      Object.entries(object).forEach(([k, v]) => {
        if (v && typeof v === "object") {
          this.clean(v);
        }
        if (
          (v && typeof v === "object" && !Object.keys(v).length) ||
          v === null ||
          v === undefined
        ) {
          if (Array.isArray(object)) {
            object.splice(k, 1);
          } else {
            delete object[k];
          }
        }
      });
      return object;
    },
    formatSelect: function(data) {
      let arr = [];
      for (let i = 0; i < data.length; i++) {
        let newObj = {
          id: data[i].id,
          text: data[i].id + " - " + data[i].text
        };
        arr.push(newObj);
      }
      return arr;
    },
    addNumberingToArr: function(arr) {
      for (let i = 0; i < arr.length; i++) {
        arr[i].no = i + 1;
      }
      return arr;
    }
  }
};
