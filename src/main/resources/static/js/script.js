// 获取所有可编辑单元格
const editableCells = document.querySelectorAll('td[contenteditable="true"]');

// 为每个可编辑单元格添加事件监听器
editableCells.forEach(cell => {
    cell.addEventListener('input', () => {
        // 处理用户输入，更新表格内容
        // 这里您可以根据需要将数据保存到后端服务器或本地存储中
        console.log('用户输入:', cell.textContent);
});
});
