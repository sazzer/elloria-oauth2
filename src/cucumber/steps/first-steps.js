const expect = require('chai').expect;

module.exports = function () {
    this.Given(/^that I start with (\d+)$/, function (initial) {
        this.context.value = parseInt(initial);
    });

    this.When(/^I add (\d+)$/, function (toAdd) {
        this.context.value += parseInt(toAdd);
    });

    this.Then(/^I end up with (\d+)$/, function (expected) {
        expect(this.context.value).to.equal(parseInt(expected));
    });
};
