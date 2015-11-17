'use strict';

const request = require('supertest');
const rootRequire = require('root-require');
const mockery = require('mockery');
const sinon = require('sinon');
const moment = require('moment-timezone');

module.exports = function() {
    this.World = function World() {
        this.context = {};

        this._mockMoment = sinon.stub().returns(moment('2014-06-01T12:00:00Z'));
        mockery.registerMock('moment-timezone', this._mockMoment);

        this.request = () => {
            return request(this._app);
        };
    }

    this.Before(function(scenario) {
        mockery.enable({
            warnOnReplace: false,
            warnOnUnregistered: false,
            useCleanCache: true
        });

        this._app = rootRequire('src/main/server')();
    });

    this.After(function(scenario) {
        mockery.disable();
    });
}
