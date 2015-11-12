const gulp = require('gulp');
const server = require('gulp-develop-server');
const eslint = require('gulp-eslint');

gulp.task('lint:main', () => {
    return gulp.src('src/main/**/*.js')
        .pipe(eslint({
            configFile: './eslintrc'
        }))
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
});

gulp.task('unit-test');
gulp.task('integration-test');

gulp.task('build', ['lint:main', 'unit-test']);
gulp.task('test', ['build', 'unit-test', 'integration-test']);

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
