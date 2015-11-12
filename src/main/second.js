/**
 * Implementation of the Lambda below. This is to prove that ESDoc works
 * @return {String} The string 'Hello, World'
 */
function lambdaImpl() {
    return 'Hello, World';
}

module.exports = {
    lambda: () => {
        return lambdaImpl();
    }
}
