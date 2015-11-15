'use strict';

const expect = require('chai').expect;

const PingColumnMapping = {
    'Status Code': {
        name: 'statusCode',
        parser: parseInt
    },
    'Content Type': {
        name: 'type'
    }
};

module.exports = function () {
    this.When(/^I ping the debug controller$/, function(done) {
        this.request()
            .get('/api/debug/ping')
            .end((err, res) => {
                this.context.pingResponse = res;
                done();
            });
    });

    this.Then(/^the ping response matches:$/, function(expected) {
        const data = new Map();
        const expectedData = expected.rowsHash();
        Object.keys(expectedData).forEach((key) => {
            const mapping = PingColumnMapping[key] || {name: key.toLowerCase()};
            let value = expectedData[key];
            if (mapping.parser) {
                value = mapping.parser(value);
            }

            data.set(mapping.name, value);
        });

        data.forEach((value, key) => {
            expect(this.context.pingResponse).to.have.property(key, value);
        });
    });
};
