var HashMap = function () {
  let obj = {};

  return {
    put: function (k, v) {
      obj[k] = v;
    },
    keys: function (k) {
      return Object.keys(obj);
    },
    contains: function (key) {
      for (let k in obj) {
        if (k === key) {
          return true;
        }
      }
      return false;
    },
    get: function (k) {
      return obj[k];
    },
    clear: function () {
      obj = {};
    },
  };
};
