function unquoteString(input) {
    const [start, end] = [input.indexOf('«'), input.indexOf('»')];
    return end > start ? input.slice(start + 1, end) : input;
}

console.log(unquoteString('«Тестик»'));
console.log(unquoteString('текст «Тестик» текст справа'));
console.log(unquoteString('Тестик'));