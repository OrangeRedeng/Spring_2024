function removeDuplicates(str) {

	let chars = [];
	let result = '';

	for (let i = 0; i < str.length; i++) {

		let hasElement = charsHasThisElement(chars, str[i]);

		if (!hasElement) {

			result = result + str[i];

			chars = addElementToArray(chars, str[i]);
		}
	}

	return result;
}


function charsHasThisElement(chars, element) {
	for (let i = 0; i < chars.length; i++) {

		if (chars[i] === element)
			return true;
	}

	return false;
}


function addElementToArray(chars, element) {

	let len = chars.length + 1;
	chars[len] = element;

	return chars;
}


console.log(removeDuplicates("asdfasdfasdfssfs"));
console.log(removeDuplicates("asd fasdfas dfssf s"));
console.log(removeDuplicates("18765345%^&*()(*&^%$#6789nbvcdfghj"));




// function removeDuplicates(str) {

// 	const chars = [];
// 	let result = '';

// 	for (let i = 0; i < str.length; i++) {

// 		if (!chars.includes(str[i])) {

// 			result = result + str[i];

// 			chars.push(str[i]);
// 		}
// 	}

// 	return result;
// }
