const expect = require('chai').expect;
const lambda = require('../main/second').lambda;

describe('Lambda', () => {
    const value = lambda();
    it('Should return the correct string', () => {
        expect(value).to.equal('Hello, World');
    });
});
