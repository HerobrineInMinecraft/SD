import os

def rename_java_to_sd(root_dir="."):
    # 遍历所有目录和文件
    for dirpath, dirnames, filenames in os.walk(root_dir):
        for filename in filenames:
            # 只处理 .java 后缀
            if filename.endswith(".java"):
                # 原文件完整路径
                old_path = os.path.join(dirpath, filename)
                # 新文件名（替换后缀）
                new_filename = filename[:-5] + ".sd"
                new_path = os.path.join(dirpath, new_filename)
                
                # 重命名
                os.rename(old_path, new_path)
                print(f"已重命名：{old_path} -> {new_path}")

if __name__ == "__main__":
    print("开始批量将 .java 文件改为 .sd...\n")
    rename_java_to_sd()
    print("\n✅ 全部完成！")