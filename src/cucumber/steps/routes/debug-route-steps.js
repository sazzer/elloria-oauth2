const expect = require('chai').expect;

module.exports = function () {
    this.When(/^I ping the debug controller$/, function(done) {
        this.request()
            .get('/api/debug/ping')
            .end((err, res) => {
                this.context.pingResponse = res;
                done();
            });
    });

    this.Then(/^the ping response is "([^"]*)"$/, function(expected) {
        expect(this.context.pingResponse).to.have.property('text', expected);
    });

    this.Then(/^the ping status code is (\d\d\d)$/, function(expected) {
        expect(this.context.pingResponse).to.have.property('statusCode', parseInt(expected));
    });

    this.Then(/^the ping content type is "([^"]*)"$/, function(expected) {
        expect(this.context.pingResponse).to.have.property('type', expected);
    });
};
