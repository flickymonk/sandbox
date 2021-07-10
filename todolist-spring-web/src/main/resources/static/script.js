//Input field for user to create new todos
const todoInput = document.getElementById("todo-input");

//<ul> with todos
const todosHtml = document.getElementById("todos");

//Array of objects representing todos with (id, text, done) properties
//It serves as a model of our data on the front-end
let todos = [];


//This function adds elements to todosHtml list and updates todos array
const addElement = todo => {
    //create new list item
    const li = document.createElement('li');

    //create text node and add it to li
    li.appendChild(document.createTextNode(todo.text));

    //Append li to ul
    todosHtml.appendChild(li);

    //Add event listener that toggles css class 'checked' on li when we click on it
    li.addEventListener('click', () => {
        li.classList.toggle('checked');
        todo.done = !todo.done;
    });

    //Add data to the array
    todos.push(todo);
};

//This function creates new list element from the value of the input field
const createTodo = () => {
    const inputValue = todoInput.value; //retrieve input

    //Validate input - we don't need empty text
    if (inputValue === '') {
        alert("You must write something!");
        return;
    }

    const newTodo = {
        text: inputValue,
        done: false //initial value is false
    };
    //In POST request - send new object to the server
    fetch('todo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newTodo)
    }).then(result => result.json()).then(result => {
        //server endpoint responds with [id] from the database
        newTodo.id = result.id;
        addElement(newTodo);
        todoInput.value = '';
    }).catch(reason => console.error(reason));
};

const deleteDone = () => {

    //Get todos with [done] == true
    const done = todos.filter(todo => todo.done);

    //We use PUT request to "delete" todos, since DELETE method doesn't specify request body in HTTP standard
    //What is actually done is we set done to true in the database
    //so it doesn't return us done items when we reload page
    fetch('todo', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(done)
    }).then(() => {
        //We retrieve all <li> which have class 'checked' and construct an array from the NodeList we get.
        const checkedLIs = Array.from(todosHtml.getElementsByClassName('checked'));
        //We remove all the items we no longer need from the DOM
        checkedLIs.forEach(li => li.remove());
        //We reassign todos array to its subset of items which are not yet done
        todos = todos.filter(todo => !todo.done);
    }).catch(reason => console.error(reason))
};

//Adds event listeners to buttons and text fields
const setupListeners = () => {
    //Pressing Enter on todoInput should add new item to the list
    todoInput.addEventListener('keyup', event => {
        if (event.key === 'Enter') {
            createTodo();
        }
    });
    //clicking on add button should add new item to the list
    document.getElementById('add').addEventListener('click', createTodo);
    //clicking on clear button should remove completed todos from the list
    document.getElementById('clear').addEventListener('click', deleteDone);
};

//When page loads, we send GET request to the server
fetch('todo')
    .then(result => result.json())
    .then(result => { //if request was successfully served, we get back the data

        //for each of todos we get back from server, we create a list element with corresponding text
        result.forEach(addElement);

        //After this, we can work with our list, thus we add event listeners to our controls.
        setupListeners();

    })
    .catch(reason => {
        //Any errors we just log
        console.error(reason);
    });
