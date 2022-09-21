$(function () {
    $('table').tablesort();

    // 削除ボタン
    $('button[name="deleteButton"]').on('click', function() {
      // モーダルダイアログ表示
      $('#deleteModal').modal('show');

      // モーダルダイアログに設定
      $('#delAssigneeName').text('・配属先　：' + $(this).attr("data-assigneeName"));
      //改善　$('form[name="deleteFormModal"]').attr("action", "/assignee/delete/" + $(this).attr("data-assigneeId") + "/");
      $('form[name="deleteFormModal"]').attr("action", "/assignee/delete/" + $(this).attr("data-assigneeId") + "/" + $(this).attr("data-assigneeName"));
      $

    });

    // 編集ボタン
    $('button[name="editorButton"]').on('click', function() {
          // モーダルダイアログ表示
          $('#updateModal').modal('show');

          // モーダルダイアログに設定
          $('#updateAssigneeId').val($(this).attr("data-assigneeId"));
          $('#updateAssigneeName').val($(this).attr("data-assigneeName"));
    });

    // 更新ボタン
    $('button[name="updateButton"]').on('click', function() {
        $('form[name="updateFormModal"]').attr("action", "/assignee/update/" + $('#updateAssigneeId').val() + "/" + $('#updateAssigneeName').val() + "/");
        $('form[name="updateFormModal"]').submit();
    });

});