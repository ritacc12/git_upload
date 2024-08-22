var HashMap = function () {
  let obj = {};

  return {
    put: function (k, v) {
      obj[k] = v;
    },
    keys: function () {
      return Object.keys(obj);
    },
    contains: function (key) {
      return key in obj ;

      /* 原先的方式
       for (let k in obj) {
         if (k === key) {
           return true;
         }
       }
     return false;*/

    },
    get: function (k) {
      return obj[k];
    },
    clear: function () {
      obj = {};
    },
  };
};
