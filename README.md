# Dictionary

# Description
- Source code chính ở trong package sample, package images lưu hình ảnh dùng cho dictionary.
- Chức năng phát âm sử dụng thư viện ffreetts.
- Có các chức năng tra từ, thêm, sửa, xóa từ, phát âm tiếng anh, dịch sử dụng gg api.
- Các icon biểu thị các chức năng từ trái qua phải là thêm, sửa, xóa, dịch.
- Ô trống textfield sử dụng để tra từ.

# Detail
- Khi bấm vào icon thêm hoặc sửa sẽ đều hiện ra cùng 1 scene mới, có các ô trống để điền từ word, type word, spelling, mean biểu thị các đặc điểm của từ. Muốn thêm từ hay sửa từ sẽ điền vào các ô trống đó để thao tác. Sau khi điền xong để lưu lại sẽ bấm vào ô add với thao tác thêm từ, bấm vào ô change với thao tác sửa từ. Bấm back để có thể quay lại scenen chính.
- Muốn xóa từ sau khi tìm được từ muốn xóa chỉ cần bấm vô icon xóa.
- Để sử dụng chức năng dịch sẽ bấm vô icon dịch sẽ hiện ra 1 scenne mới. Có thể lựa chọn giữa các ngôn ngữ mình muốn dịch. Bấm back để quay lại scene chính.

# Note: Trong class DictionaryManagement hằng số DATA_FILE_PATH phải set bằng đường dẫn tuyệt đối đến tệp E_V.txt
