window.onload = function (e) {
    fetch('/srv/user/all')
        .then(data => data.json())
        .then(json => document.getElementById('users').value = JSON.stringify(json))
        .catch(error => console.error(error));

    fetch('/srv/survey/all')
        .then(data => data.json())
        .then(json => document.getElementById('surveys').value = JSON.stringify(json))
        .catch(error => console.error(error));
}