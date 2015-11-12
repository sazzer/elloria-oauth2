const gulp = require('gulp');
const server = require('gulp-develop-server');

gulp.task('unit-test');
gulp.task('integration-test');

gulp.task('build', ['unit-test']);
gulp.task('test', ['unit-test', 'integration-test']);

gulp.task('start', ['build'], () => {
    server.listen({path: './src/main/index.js'});
});

gulp.task('watch:start', ['start'], () => {
    gulp.watch('src/**/*', ['start', server.restart]);
});

gulp.task('watch:build', () => {
    gulp.watch('src/**/*', ['build']);
});

gulp.task('default', ['build']);
