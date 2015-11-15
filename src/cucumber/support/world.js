const request = require('supertest');
const rootRequire = require('root-require');

module.exports = function() {
    this.World = function World() {
        this.context = {};

        this._app = rootRequire('src/main/server')();

        this.request = () => {
            return request(this._app);
        };
    }
}
