'use strict';

const moment = require('moment-timezone');

module.exports = function () {
    this.Given(/^the current time is (.+)$/, function (currentTime) {
        this._mockMoment.returns(moment(currentTime));
    });
};
