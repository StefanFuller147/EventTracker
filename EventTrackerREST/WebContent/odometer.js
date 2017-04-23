$(document).ready(function() {
  config();
});

var config = function() {
  var myReq = $.ajax({
    type: 'GET',
    url: 'api/odometers',
    dataType: 'json'
  });

  myReq.done(function(data, status) {
    for (var i = 0; i < data.length; i++) {}
    buildTable(data);
  });

  myReq.fail(function() {
    console.log('YOU DON MESSED UP BROH!');
    console.log(myReq.status);
  });
};

var buildTable = function(data) {
  var $table = $('<table>');
  $table.attr('id', 'table')
  $('body').append($table);

  var $th = $('<thead>');
  $table.append($th);

  var $headerRow = $('<tr>');
  $th.append($headerRow);
  $headerRow.text('Average miles driven per week');
  var $split = $('<hr>');
  $headerRow.append($split);
  $th.append($headerRow);

  var $tb = $('<tbody>');
  $th.append($tb);

  data.forEach(function(v, i, a) {

    var $tr = $('<tr>');
    $tb.append($tr);

    var $td1 = $('<td>');
    $td1.attr('id', 'name');
    $td1.text(v.name);
    $tr.append($td1);

    var $view = $('<button>');
    $view.text('View Car');
    $view.addClass('button');
    $view.attr('id', 'view');
    $tr.append($view);

    var $delete = $('<button>');
    $delete.text('Delete');
    $delete.addClass('button');
    $delete.attr('id', 'delete');
    $tr.append($delete);

    $($delete).on('click', function(e) {
      e.preventDefault();
      console.log('in delete event listener');
      deleteOdometer(v.id, $(this));
    });

    $($view).on('click', function(e) {
      e.preventDefault();
      console.log('In view event listener');
      view(v.id);
    });

  });




  var $create = $('<button>');
  $create.text('Make a car');
  $create.addClass('button');
  $create.attr('id', 'create');

  var $createButtonDiv = $('<div>');
  $createButtonDiv.attr('id', 'createButtonDiv')
  $createButtonDiv.append($create);
  $('body').append($createButtonDiv)

  $($create).on('click', function(e) {
    e.preventDefault();
    console.log('in create event listener');
    createCar(data);
  });

}




var view = function(id, data) {
  goBack();
  $('#create').remove();
  var myReq = $.ajax({
    type: 'GET',
    url: 'api/odometers/' + id,
    dataType: 'json'
  });

  myReq.done(function(data, status) {
    $('#table').remove();

    var $table = $('<table>');
    $table.attr('id', 'singleCar')

    $('body').append($table);

    var $th = $('<thead>');
    $table.append($th);

    var $headerRow = $('<tr>');
    $th.append($headerRow);
    $headerRow.text(data.name);
    var $split = $('<hr>');
    $headerRow.append($split);
    $th.append($headerRow);

    var $tb = $('<tbody>');
    $th.append($tb);



    var $tr1 = $('<tr>');
    $tr1.text('Starting Mileage: ' + data.startingOdometer);
    $tb.append($tr1);

    var $tr2 = $('<tr>');
    $tr2.text('Ending Mileage: ' + data.endingOdometer);
    $tb.append($tr2);

    var $tr3 = $('<tr>');
    $tr3.text('Days driven: ' + data.days);
    $tb.append($tr3);

    var $tr4 = $('<tr>');
    $tr4.text('Average miles per day: ' + data.average);
    $tb.append($tr4);




  });


  myReq.fail(function() {
    console.log('SOMETHING BROKE!');
    console.log(myReq.status);
  });
};

var deleteOdometer = function(id, btn) {

  var myReq = $.ajax({
    type: 'DELETE',
    url: 'api/odometers/' + id
  });
  myReq.done(function(data, status) {
    console.log('in delete function');
    btn.parent().remove();
  });

  myReq.fail(function() {
    console.log('SOMETHING BROKE!');
    console.log(myReq.status);

  });

}

var goBack = function() {
  var $div = $('<div>');
  $('body').append($div);

  var $goBack = $('<button>');
  $goBack.text('Go back');
  $goBack.addClass('button')
  $goBack.attr('id', 'goBack');
  $div.append($goBack)

  $($goBack).on('click', function(e) {
    e.preventDefault();
    $('#table').remove();
    $('#h1').remove();
    $('#carDiv2').remove();
    $('#create ').remove();
    $('#createForm').remove();
    $('#carDiv').remove();
    $('#singleCar').remove();

    config();
    $goBack.remove();
  });
}

var createCar = function(data) {
  goBack();
  $('#table').hide();
  $('#create').remove();

  var $br = $('<br>');

  var $div = $('<div>');
  $div.attr('id', 'CarDiv2');
  $('body').append($div);

  var $form = $('<form>');
  $form.attr('name', 'create');
  $form.attr('id', 'createForm')
  $div.append($form);

  var $input1 = $('<input>');
  $input1.attr('id', 'createCar')
  $input1.attr('type', 'text');
  $input1.attr('name', 'name');
  $input1.attr('id', 'nameOfCar')
  $input1.attr('placeHolder', 'Car Name Here');
  $input1.append($br)
  $form.append($input1);


  var $input2 = $('<input>');
  $input2.attr('type', 'text');
  $input2.attr('name', 'so');
  $input2.attr('id', 'so')
  $input2.attr('placeHolder', 'Starting Odometer');
  $input2.append($br)

  $form.append($input2);


  var $input3 = $('<input>');
  $input3.attr('type', 'text');
  $input3.attr('name', 'eo');
  $input3.attr('id', 'eo')
  $input3.attr('placeHolder', 'Ending Odometer');
  $input3.append($br)

  $form.append($input3);


  var $input4 = $('<input>');
  $input4.attr('type', 'text');
  $input4.attr('name', 'days');
  $input4.attr('id', 'daysDriven')
  $input4.attr('placeHolder', 'Days driven');
  $input4.append($br)

  $form.append($input4);

  var $submit = $('<button>');
  $submit.text('Submit Car');
  $submit.addClass('button');
  $submit.attr('id', 'submit');
  $form.append($submit);

  $($submit).on('click', function(e) {
    e.preventDefault();
    console.log($('#so').val());


    var car = {
      name: $('#nameOfCar').val(),
      startingOdometer: $('#so').val(),
      endingOdometer: $('#eo').val(),
      days: $('#daysDriven').val()
    };

    var myReq = $.ajax({
      type: "POST",
      url: 'api/odometers',
      dataType: "json",
      contentType: 'application/json',
      data: JSON.stringify(car)
    });

    myReq.done(function(data, status) {
      config();
      console.log(car);
      $('#createCarDiv').remove();
      $('#table').remove();
      $('#carDiv2').remove();
      $('#createForm').remove();
      $('#create').remove();
      $('#goBack').remove();

    });

    myReq.fail(function(xhr, status, error) {
      console.log('YOU DON MESSED UP BROH!!');
      console.log(error);
      console.log(status);

    });

  });
}



// var $h1 = $('<h1>')
//
// var $ul = $('<ul>');
// $ul.text(data.name)
// var $div = $('<div>');
// $div.attr('id', 'carDiv');
//
// $div.append($ul)
// $('body').append($div);




// for (var i = 0; i < data.length; i++) {

//   var $li1 = $('<li>');
//   console.log(data);
//   // $li1.append(data.startingOdometer);
//   $li1.text(data.startingOdometer);
//   $ul.append($li1);
//
//   var $li2 = $('<li>');
//   console.log(data);
//   // $li2.append(data.endingOdometer);
//   $li2.text(data.endingOdometer);
//   $ul.append($li2);
// //}
//
//   var $li3 = $('<li>');
//   console.log(data);
//   // $li3.append(data.days);
//   $li3.text(data.days);
//   $ul.append($li3);
// //}
//
//   var $li4 = $('<li>');
//   console.log(data);
//   // $li4.append(data.average);
//   $li4.text(data.average);
//   $ul.append($li4);
// //}
